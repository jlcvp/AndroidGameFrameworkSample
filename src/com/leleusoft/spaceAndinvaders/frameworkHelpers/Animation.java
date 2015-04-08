package com.leleusoft.spaceAndinvaders.frameworkHelpers;

import java.util.ArrayList;

import com.leleusoft.gameframework.Image;


public class Animation {

    protected ArrayList<AnimFrame> frames;
    protected int currentFrame;
    protected long animTime;
    protected long totalDuration;

    public Animation() {
        frames = new ArrayList<AnimFrame>();
        totalDuration = 0;

        synchronized (this) {
            animTime = 0;
            currentFrame = 0;
        }
    }

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    public synchronized void update(long elapsedTime) {
        if (frames.size() > 1) {
            animTime += elapsedTime;
            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;
                currentFrame = 0;

            }

            while (animTime > getFrame(currentFrame).endTime) {
                currentFrame++;

            }
        }
    }

    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        } else {
            return getFrame(currentFrame).image;
        }
    }

    protected AnimFrame getFrame(int i) {
        return frames.get(i);
    }

    class AnimFrame {

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}