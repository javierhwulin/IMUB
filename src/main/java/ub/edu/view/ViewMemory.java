package ub.edu.view;

public class ViewMemory {
    private Escena mainScene;
    private String filterTypeValue;
    private String filterStrategyValue;
    private String filterTypeObraAudioVisual;
    private String filterTypeTop;
    public ViewMemory() {
    }
    public Escena getMainScene() {
        return mainScene;
    }
    public void setMainScene(Escena mainScene) {
        this.mainScene = mainScene;
    }
    public String getFilterTypeValue() {
        return filterTypeValue;
    }
    public void setFilterTypeValue(String filterTypeValue) {
        this.filterTypeValue = filterTypeValue;
    }
    public String getFilterStrategyValue() {
        return filterStrategyValue;
    }
    public void setFilterStrategyValue(String filterStrategyValue) {
        this.filterStrategyValue = filterStrategyValue;
    }
    public String getFilterTypeObraAudioVisual() {
        return filterTypeObraAudioVisual;
    }
    public void setFilterTypeObraAudioVisual(String filterTypeObraAudioVisual) {
        this.filterTypeObraAudioVisual = filterTypeObraAudioVisual;
    }
    public String getFilterTypeTop() {
        return filterTypeTop;
    }
    public void setFilterTypeTop(String filterTypeTop) {
        this.filterTypeTop = filterTypeTop;
    }
}
