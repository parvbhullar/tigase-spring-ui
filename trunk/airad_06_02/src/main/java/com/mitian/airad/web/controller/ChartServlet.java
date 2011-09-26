package com.mitian.airad.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jofc2.model.Chart;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.LineChart;

/**
 * Servlet implementation class InfoServlet
 */
public class ChartServlet extends HttpServlet {

    /**
     * 实例样式：http://sarin.javaeye.com/blog/685354 || http://apps.hi.baidu.com/share/detail/6608388
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8;");
        String d = request.getParameter("do");

        if ("doopen".equals(d)) {
            doopen(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }

    protected void doopen(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // y轴数据集合-使用数量
        List<Number> dataSet = new ArrayList<Number>();
        // x轴数据集合-浏览器类型
        List<Label> xLabel = new ArrayList<Label>();
        // 获取需要显示的数据集
        // List browserList = getServMgr().getVisitStatService().getBrowserStat();
        // for (int i = 0; i < browserList.size(); i++) {
        // Map map = (Map) browserList.get(i);
        // // 填充x轴
        // dataSet.add((Integer) map.get("statCount"));
        // // 填充y轴
        // xLabel.add(new Label((String) map.get("statVar")));
        // }
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            double val = (random.nextDouble() * i * 100 + random.nextDouble());
            dataSet.add(val);
            xLabel.add(new Label("lab" + i));
        }
        // 设置X轴内容
        XAxis labels = new XAxis();
        labels.addLabels(xLabel);
        // 设置Y轴显示值域:Range的三个参数含义为：坐标最小值，最大值和步进值
        YAxis range = new YAxis();
        range.setRange(0, 200, 10);
        // OFC折线图设置
        LineChart lineChart = new LineChart(LineChart.Style.NORMAL);
        lineChart.addValues(dataSet);
        lineChart.setColour("#6666FF");
        lineChart.setText("使用者数量");
        // 图表设置
        Chart charts = new Chart("用户浏览器使用量分布");
        charts.setXAxis(labels);
        charts.setYAxis(range);
        charts.addElements(lineChart);
        // 打印JSON格式的文本
        // System.out.print(charts);
        // System.out.println(charts + "---");
        // HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json-rpc;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");
        response.getWriter().write(charts.toString());
    }
}
