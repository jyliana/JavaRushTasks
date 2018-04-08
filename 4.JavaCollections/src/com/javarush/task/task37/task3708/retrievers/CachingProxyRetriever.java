package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    Storage storage;
    OriginalRetriever originalRetriever;
    LRUCache cache = new LRUCache(10);

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        this.originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object result;
        result = cache.find(id);
        if (result == null) {
            result = originalRetriever.retrieve(id);
            cache.set(id, result);
        }
        return result;
    }
}
