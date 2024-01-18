package digi.coders.quizesapps.Model;

public class RecyclerModel {

    private String itemName;
    private boolean isChecked;

    public RecyclerModel() {
    }

    public RecyclerModel(String itemName, boolean isChecked) {
        this.itemName = itemName;
        this.isChecked = isChecked;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
