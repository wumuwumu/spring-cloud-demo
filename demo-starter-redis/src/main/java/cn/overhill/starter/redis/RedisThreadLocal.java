package cn.overhill.starter.redis;

import java.util.ArrayDeque;
import java.util.Deque;

public class RedisThreadLocal {

    public static ThreadLocal<Deque<Integer>> dbLocal = new ThreadLocal<>();

    public static void set(int db){
        Deque<Integer> deque = dbLocal.get();
        if(deque == null){
            deque = new ArrayDeque<>();
            dbLocal.set(deque);
        }
        dbLocal.get().offerLast(db);
    }

    public static void remove(){
        dbLocal.get().removeLast();
    }

    public static Integer get(){
        Deque<Integer> deque = dbLocal.get();
        if(deque == null){
            deque = new ArrayDeque<>();
            dbLocal.set(deque);
        }
        if(deque.peekLast() == null){
            return null;
        }
       return dbLocal.get().peekLast();
    }
}
