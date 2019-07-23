<html>
<head>
    <title>销售报表-柱状图</title>
    <script src="/js/echarts/echarts.js"></script>
</head>
<body>
<div id="main" style="height:580px;width: 780px;margin:10px auto"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title : {
            text: '潜在客户报表',
            subtext: '${qo.groupName}',
            left:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['新增客户数'],
            left:'center'
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ${keyList}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'蒸发量',
                type:'bar',
                data:${valueList},
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    myChart.setOption(option);
</script>
</body>
</html>
