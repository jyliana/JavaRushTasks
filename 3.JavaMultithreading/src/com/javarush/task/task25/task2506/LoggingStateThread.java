package com.javarush.task.task25.task2506;

class LoggingStateThread extends Thread {
    private Thread thread;
    private State state;
    private State newState;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        state = thread.getState();
        System.out.println(state);

        while (!state.equals(State.TERMINATED)) {
            newState = thread.getState();
            if (!state.equals(newState)) {
                System.out.println(newState);
                state = newState;
            }
        }
    }
}