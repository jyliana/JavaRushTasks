package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvertisementManager {
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> playList = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        getBestList(new ArrayList<>(), 0);
        if (playList.isEmpty()) throw new NoVideoAvailableException();

        Collections.sort(playList, (o1, o2) -> {
            int result = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
            if (result == 0)
                result = Long.compare(o2.getAmountPerOneDisplaying() / o2.getDuration(), o1.getAmountPerOneDisplaying() / o1.getDuration());
            return result;
        });

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(playList, getTotalAmount(playList), getTotalDuration(playList)));

        for (Advertisement advertisement : playList) {
            System.out.printf("%s is displaying... %d, %d\n",
                    advertisement.getName(), advertisement.getAmountPerOneDisplaying(),
                    advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }

    private void getBestList(List<Advertisement> list, int index) {
        List<Advertisement> tempList = new ArrayList<>(list);
        if (getTotalDuration(list) <= timeSeconds) {
            if (getTotalAmount(list) > getTotalAmount(playList)) playList = new ArrayList<>(list);
            if (getTotalAmount(list) == getTotalAmount(playList) && getTotalDuration(list) > getTotalDuration(playList))
                playList = new ArrayList<>(list);
            if (index < storage.list().size()) {
                getBestList(tempList, index + 1);
                if (storage.list().get(index).getHits() > 0) {
                    tempList.add(storage.list().get(index));
                }
                getBestList(tempList, index + 1);
            }
        }
    }

    private int getTotalDuration(List<Advertisement> list) {
        int total = 0;
        for (Advertisement advertisement : list) total += advertisement.getDuration();
        return total;
    }

    private int getTotalAmount(List<Advertisement> list) {
        int total = 0;
        for (Advertisement advertisement : list) total += advertisement.getAmountPerOneDisplaying();
        return total;
    }
}
