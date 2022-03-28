package com.personal.cl.leetcode;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code1576 {

    @Test
    public void test() {
//        draw();
//        buildGif();
    }

    static class Solution {

        static class Node {
            public int val;
            public List<Node> children;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        };

        public List<Integer> postorder(Node root) {
            List<Integer> list = new ArrayList<>() ;
            this.dfs(root, list);
            return list;
        }

        private void dfs(Node root, List<Integer> list) {
            if (root == null) {
                return;
            }
            for (Node node : root.children) {
                this.dfs(node, list);
            }
            list.add(root.val);
        }
    }

    @SneakyThrows
    public void buildGif() {
        BufferedImage[] images = new BufferedImage[]{
                ImageIO.read(new File("/Users/xiaowenrou/Downloads/1.png")),
                ImageIO.read(new File("/Users/xiaowenrou/Downloads/2.png")),
        };
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.setRepeat(0);
        encoder.start(new FileOutputStream("/Users/xiaowenrou/Downloads/3.gif"));
        for (BufferedImage image : images) {
            encoder.setDelay(500);
            encoder.addFrame(image);
        }
        encoder.finish();
    }

    @SneakyThrows
    public void draw() {
        // 图像大小 315 * 315， 圆点直径 15
        final int width = 315, height = 315, len = 15;
        // 创建4通道图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 创建画布
        Graphics2D graphics = image.createGraphics();
        // 设置背景颜色
        graphics.setBackground(new Color(255, 255, 255, 0));
        graphics.clearRect(0, 0, width, height);
        // 启用抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 画笔颜色
        graphics.setColor(Color.CYAN);
        for (int i = len; i < width; i += 2 * len) {
            // top
            graphics.fillArc(i, 0, len, len, 0, 360);
            // below
            graphics.fillArc(i, height - len, len, len, 0, 360);
            // left
            graphics.fillArc(0, i, len, len, 0, 360);
            // right
            graphics.fillArc(height - len, i, len, len, 0, 360);
        }
        // 释放画布
        graphics.dispose();
        // 写文件
        ImageIO.write(image, "png", new File("/Users/xiaowenrou/Downloads/2.png"));
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = s.length();
        char[] sArr = s.toCharArray();
        int[] dArr = new int[len], lArr = new int[len], rArr = new int[len], res = new int[queries.length];
        for (int i = 0, c = 0, l = -1; i < len; i++) {
            if (sArr[i] == '*') {
                c++;
            } else {
                l = i;
            }
            dArr[i] = c;
            lArr[i] = l;
        }
        for (int i = len - 1, r = -1; i > -1; i--) {
            if (sArr[i] == '|') {
                r = i;
            }
            rArr[i] = r;
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int l = lArr[query[0]], r = rArr[query[1]];
            res[i] = l == -1 || r == -1 || l >= r ? 0 : dArr[r] - dArr[l];
        }
        return res;
    }

    class Node {
        int count;
        char c;

        Node(int count, char c) {
            this.count = count;
            this.c = c;
        }

        boolean check(StringBuilder stringBuilder) {
            if (this.count < 1) return false;
            int len = stringBuilder.length();
            return !(len > 1 && stringBuilder.charAt(len - 2) == this.c && stringBuilder.charAt(len - 1) == this.c);
        }

        void append(StringBuilder stringBuilder) {
            stringBuilder.append(this.c);
            this.count--;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        Node[] arr = new Node[]{new Node(a, 'a'), new Node(b, 'b'), new Node(c, 'c')};
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            Arrays.sort(arr, (o1, o2) -> o2.count - o1.count);
            Node node = null;
            for (Node choice : arr) {
                if (choice.check(stringBuilder)) {
                    node = choice;
                    break;
                }
            }
            if (node == null) break;
            node.append(stringBuilder);
        }
        return stringBuilder.toString();
    }

}
