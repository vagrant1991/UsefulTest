package com.vagrant.usefultest.entity;
import java.util.List;

public class ActionBean {
    public boolean success;
    public String code;
    public String total;
    public Result result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        public List<Actions> actions;

        public List<Actions> getActions() {
            return actions;
        }

        public void setActions(List<Actions> actions) {
            this.actions = actions;
        }
    }
    public static class Actions {
        public int id;
        public double dist;
        public String stime;
        public String timeStr;
        public String comments;
        public String place;
        public String contact;
        public double rmb;
        public String type;
        public String age;
        public String addtext;
        public String name;
        public String keeps;
        public String wantnum;
        public String topicname;
        public String users;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getTimeStr() {
            return timeStr;
        }
        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }
        public String getAddtext() {
            return addtext;
        }
        public void setAddtext(String addtext) {
            this.addtext = addtext;
        }

        public double getDist() {
            return dist;
        }
        public void setDist(double dist) {
            this.dist = dist;
        }
        public String getStime() {
            return stime;
        }
        public void setStime(String stime) {
            this.stime = stime;
        }
        public String getComments() {
            return comments;
        }
        public void setComments(String comments) {
            this.comments = comments;
        }
        public String getPlace() {
            return place;
        }
        public void setPlace(String place) {
            this.place = place;
        }
        public String getContact() {
            return contact;
        }
        public void setContact(String contact) {
            this.contact = contact;
        }
        public double getRmb() {
            return rmb;
        }
        public void setRmb(double rmb) {
            this.rmb = rmb;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getAge() {
            return age;
        }
        public void setAge(String age) {
            this.age = age;
        }
        public String getAddText() {
            return addtext;
        }
        public void setAddText(String addText) {
            this.addtext = addText;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getKeeps() {
            return keeps;
        }
        public void setKeeps(String keeps) {
            this.keeps = keeps;
        }
        public String getWantnum() {
            return wantnum;
        }
        public void setWantnum(String wantnum) {
            this.wantnum = wantnum;
        }
        public String getTopicname() {
            return topicname;
        }
        public void setTopicname(String topicname) {
            this.topicname = topicname;
        }
        public String getUsers() {
            return users;
        }
        public void setUsers(String users) {
            this.users = users;
        }
    }
}
