package com.example.multi.datasource.sample.sharding.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author myeongju.jung
 */
@Component
@Aspect
@Profile("shard")
public class ShardingAspect {
    private ExpressionEvaluator<?> evaluator = new ExpressionEvaluator<>();

    @Around("execution(* *.*(..)) && @annotation(sharding)")
    public Object logPrint(ProceedingJoinPoint joinPoint, Sharding sharding) throws Throwable {
        Object distributionObj = getValue(joinPoint, sharding.key());
        ShardingHolder.setKey(distributionObj.hashCode());
        try {
            return joinPoint.proceed();
        } finally {
            ShardingHolder.clear();
        }
    }

    private Object getValue(JoinPoint joinPoint, String condition) {
        return getValue(joinPoint.getTarget(), joinPoint.getArgs(),
                        joinPoint.getTarget().getClass(),
                        ((MethodSignature) joinPoint.getSignature()).getMethod(), condition);
    }

    private Object getValue(Object object, Object[] args, Class clazz, Method method, String condition) {
        if (args == null) {
            return null;
        }
        EvaluationContext evaluationContext = evaluator.createEvaluationContext(object, clazz, method, args);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
        return evaluator.condition(condition, methodKey, evaluationContext);
    }
}

class ExpressionEvaluator<T> extends CachedExpressionEvaluator {

    // shared param discoverer since it caches data internally
    private final ParameterNameDiscoverer paramNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(64);

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);

    /**
     * Create the suitable {@link EvaluationContext} for the specified event handling
     * on the specified method.
     */
    EvaluationContext createEvaluationContext(Object object, Class<?> targetClass, Method method, Object[] args) {

        Method targetMethod = getTargetMethod(targetClass, method);
        ExpressionRootObject root = new ExpressionRootObject(object, args);
        return new MethodBasedEvaluationContext(root, targetMethod, args, this.paramNameDiscoverer);
    }

    /**
     * Specify if the condition defined by the specified expression matches.
     */
    @SuppressWarnings("unchecked")
    T condition(String conditionExpression, AnnotatedElementKey elementKey, EvaluationContext evalContext) {
        return (T) getExpression(this.conditionCache, elementKey, conditionExpression).getValue(evalContext);
    }

    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(methodKey);
        if (targetMethod == null) {
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            this.targetMethodCache.put(methodKey, targetMethod);
        }
        return targetMethod;
    }
}

@SuppressWarnings("unused")
class ExpressionRootObject {
    private final Object object;

    private final Object[] args;

    ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }
}

