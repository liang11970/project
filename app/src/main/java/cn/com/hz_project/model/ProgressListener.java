package cn.com.hz_project.model;

public interface ProgressListener {
        void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);
    }
