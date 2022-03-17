package dev.aapy.tablist;

public class TabThread extends Thread {

    private final Tab htab;

    public TabThread(Tab htab) {
        this.htab = htab;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            //Tick
            try {
                tick();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            //Thread Sleep
            try {
                sleep(250L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        for (PlayerTablist tablist : htab.getTablists().values()) {
            tablist.update();
        }
    }
}
