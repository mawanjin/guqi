package com.thinkgem.jeesite.test;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;

/**
 * 中文分词测试
 */
public class WordSplitText {

    public static void main(String[] args) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokens = segmenter.process("测试一下结巴分词", JiebaSegmenter.SegMode.SEARCH);
        for (SegToken segToken : segTokens) {
            System.out.println(segToken.word);
        }


    }
}
