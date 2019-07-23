
<html>
<head>
    <title>销售报表-柱状图</title>
    <script src="/js/echarts/echarts.js"></script>

</head>
<body>
<div id="main" style="height:580px;width: 780px;margin:10px auto"></div>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title : {
                text: '潜在客户报表',
                subtext: '${qo.groupName}',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ${keyList}
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:${datas}   ,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 为echarts对象加载数据
        myChart.setOption(option);
</script>
</body>
</html>
