public class ThreadTimer extends Thread{
    private SceneOfResult sceneTransition;
    public ThreadTimer(SceneOfResult scene){
        this.sceneTransition = scene;
    }


    public void run(){
        try {
            Thread.sleep(10000);
            this.sceneTransition.transitionToWindow();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void hello(){
        System.out.println("hey");
    }
}
