package com.parakeetstudios.tilescape.managers;

import net.minecraft.util.Tuple;

import java.util.*;

public class CentralQueueRegistry {

    private final HashMap<String, QueueManager> registeredQueues = new HashMap<>();
    private final Set<UUID> allQueuedPlayers = new HashSet<>();

    public QueueManager getQueue(String queueName) {
        return registeredQueues.get(queueName);
    }

    public void registerQueue(QueueManager queue) {
        registeredQueues.put(queue.queueName, queue);
    }

    public void unregisterQueue(String queueName) {
        registeredQueues.remove(queueName);
    }

    public void unregisterQueue(QueueManager queue) {
        registeredQueues.remove(queue.queueName);
    }

    public void playerJoinedQueue(UUID playerID) {
        allQueuedPlayers.add(playerID);
    }

    public void playerLeftQueue(UUID playerID) {
        allQueuedPlayers.remove(playerID);
    }

    public boolean isPlayerInQueue(UUID playerID) {
        return allQueuedPlayers.contains(playerID);
    }


    public Set<QueueManager> getRegisteredQueues() {
        return new HashSet<>(registeredQueues.values());
    }

    public List<String> getActiveQueueNames() {
        List<String> activeQs = new ArrayList<>();
        for (QueueManager q : registeredQueues.values()) {
            if (q.isActive) {
                activeQs.add(q.queueName.toLowerCase());
            }
        }
        return activeQs;
    }

}
