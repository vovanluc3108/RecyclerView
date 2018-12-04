package data.model;

public class Language {
    private String mName;

    public Language(String name) {
        mName = name;
    }

    public Language() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
