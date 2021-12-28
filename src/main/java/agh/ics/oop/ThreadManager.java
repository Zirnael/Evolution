package agh.ics.oop;

import javafx.scene.control.Button;

public class ThreadManager {

    public boolean going = false;
    private Simulation simulation;
    private Button button;
    public Thread thread;

    public ThreadManager(Simulation sim, Button b){
        this.button = b;
        this.simulation = sim;
    }
    public void click() {
        if (this.going){
            this.thread.interrupt();
            while(this.thread.isAlive()){
                System.out.println("wait");
            }
            this.going = false;
            this.button.setText("Start Simulation");
        }
        else{
            this.button.setText("Stop Simpulation");
            this.thread = new Thread(this.simulation);
            this.thread.start();
            this.going = true;
        }
    }
}
