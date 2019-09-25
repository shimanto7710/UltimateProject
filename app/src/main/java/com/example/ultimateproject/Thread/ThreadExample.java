package com.example.ultimateproject.Thread;

public class ThreadExample {

    //thread can be possible with runnable and without runnable
    //handle thread use to do background work and after doing background thread then do UI change
    //handler also use runnable
    //async task thread do same as handler


    public void examples() {
        //its a basic thread (very basic)

        //Example one with runnable
        // runnable is a interface which is intented to run in a thread
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


        //example 2 with Runnable
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();

        //there is a difference between thread.start() and thread.run() function start() create the thread and start and run() only run the thread
    }


    //without runnable
    public void example(){

        //example thread 1
        new Thread("name"){
            public void run(){
                //do something
            }
        }.start();


        //example 2
        Thread thread=new Thread(""){
            public void run(){
                //do something
            }
        };

        thread.start();
    }


}
