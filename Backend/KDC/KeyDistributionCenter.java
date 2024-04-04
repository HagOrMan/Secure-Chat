package Backend.KDC;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KeyDistributionCenter {

    // Scheduler which will regularly update the keys using java threads.
    private ScheduledExecutorService scheduler;
    private KeyDBConnector dbConnector;

    public KeyDistributionCenter(){
        dbConnector = new KeyDBConnector();

        // Initializes object with a scheduler and immediately sets it to refresh keys every hour.
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::updateKeys, 0, 1, TimeUnit.HOURS);
    }

    // Shuts down the scheduler because threads are finicky and want to make sure there's nothing running when it shouldn't be.
    public void shutDownScheduler() {
        
        // Ensures we don't call this on a shut down scheduler
        if (scheduler.isShutdown()) {
            scheduler.shutdown();

            // Because the above method waits for tasks to finish, this next block basically tells it that it can only take so long before a force shut down/
            try {

                // Giving it 30 seconds to finish.
                if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                    // Force the shutdown if 30 seconds have passed without tasks finishing (maybe we decide if there are tasks stacked up, we should let them finish?).
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                // Interrupts the thread if any exception occurs to make sure that the scheduler gets shut down.
                Thread.currentThread().interrupt();
            }
        }
    }

    public Key createKey(String user){
        Encrypter.encryptKey(null, null);
        return null;
    }

    /**
     * Removes the specified key from key storage
     * @param key the key to be removed
     * @return true if key exists and was removed, false if key does not exist in storage
     */
    public boolean removeKey(Key key){
        return dbConnector.removeKey(key);
    }

    private void updateKeys(){

    }
    
}
