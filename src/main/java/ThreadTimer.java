public class ThreadTimer extends Thread{
    private SceneOfResult sceneTransition;
    public static final int TIME_OF_REFRESH =10000;

    public ThreadTimer(SceneOfResult scene){
        this.sceneTransition = scene;
    }


    public void run(){
        try {
            Thread.sleep(TIME_OF_REFRESH);
            this.sceneTransition.transitionToWindow();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
