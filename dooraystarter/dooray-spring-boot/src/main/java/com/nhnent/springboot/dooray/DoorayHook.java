package com.nhnent.springboot.dooray;


import java.util.List;

/**
 * {"botName": "BotName", "botIconImage": "https://translate.nhnent.com/icon/botimage.jpg", "text":"markdown text!\n* item1\n* item2","attachments":[{"title":"title!", "titleLink": "http://dooray.com", "text":"markdown text!!\n* item3\n* item4", "color": "darkgreen"}]}
 */
public class DoorayHook {
    private String botName;
    private String botIconImage;
    private String text;
    private List<Attachment> attachments;

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotIconImage() {
        return botIconImage;
    }

    public void setBotIconImage(String botIconImage) {
        this.botIconImage = botIconImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public static class Attachment{
        private String title;
        private String titleLink;
        private String text;
        private String color;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleLink() {
            return titleLink;
        }

        public void setTitleLink(String titleLink) {
            this.titleLink = titleLink;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}
