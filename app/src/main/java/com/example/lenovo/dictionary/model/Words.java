package com.example.lenovo.dictionary.model;


 public class Words {

    private boolean isChinese;

    private String key;

    private String fy;

    private String psE;

    private String pronE;

    private String psA;

    private String pronA;

    private String posAcceptation;

    private String sent;

    public Words() {
        this.key = "";
        this.fy = "";
        this.psE = "";
        this.pronE = "";
        this.psA = "";
        this.pronA = "";
        this.posAcceptation = "";
        this.sent = "";
        this.isChinese = false;
    }

    public Words(boolean isChinese, String key, String fy, String psE,
                 String pronE, String psA, String pronA, String posAcceptation, String sent) {
        this.isChinese = isChinese;
        this.key = key;
        this.fy = fy;
        this.psE = psE;
        this.pronE = pronE;
        this.psA = psA;
        this.pronA = pronA;
        this.posAcceptation = posAcceptation;
        this.sent = sent;
    }

    public boolean getIsChinese() {
        return isChinese;
    }

    public void setIsChinese(boolean isChinese) {
        this.isChinese = isChinese;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getPsE() {
        return psE;
    }

    public void setPsE(String psE) {
        this.psE = psE;
    }

    public String getPronE() {
        return pronE;
    }

    public void setPronE(String pronE) {
        this.pronE = pronE;
    }

    public String getPsA() {
        return psA;
    }

    public void setPsA(String psA) {
        this.psA = psA;
    }

    public String getPronA() {
        return pronA;
    }

    public void setPronA(String pronA) {
        this.pronA = pronA;
    }

    public String getPosAcceptation() {
        return posAcceptation;
    }

    public void setPosAcceptation(String posAcceptation) {
        this.posAcceptation = posAcceptation;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }
}

