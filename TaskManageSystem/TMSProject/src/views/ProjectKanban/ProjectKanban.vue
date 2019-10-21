<template>
  <div class="ProjectKanban">
    <el-col :span="6" class="leftBox">
      <div id="myChart" :style="{width: '500px', height: '600px'}"></div>
    </el-col>
    <el-col :span="18">
      <div id="myChart2" :style="{width: '100%', height: '600px'}"></div>
    </el-col>
  </div>
</template>

<script>
import echarts from "echarts";
export default {
  data() {
    return {
      number: 122
    };
  },
  mounted() {
    let that = this;
    let myChart = echarts.init(document.getElementById("myChart"));
    myChart.setOption({
      legend: {
        orient: "vertical",
        left: "left",
        data: ["进行中", "测试中", "已完成", "未开启", "已暂停"]
      },
      series: [
        {
          name: "访问来源",
          type: "pie",
          radius: "55%",
          center: ["60%", "30%"],
          data: [
            { value: 335, name: "进行中" + this.number },
            { value: 310, name: "测试中" },
            { value: 234, name: "已完成" },
            { value: 135, name: "未开启" },
            { value: 135, name: "已暂停" }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)"
            }
          }
        }
      ]
    });
    let myChart2 = echarts.init(document.getElementById("myChart2"));
    myChart2.setOption({
      title: {
        text: "任务级别"
      },
      color: ["#3398DB"],
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      xAxis: [
        {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
          axisTick: {
            alignWithLabel: true
          }
        }
      ],
      yAxis: [
        {
          type: "value"
        }
      ],
      series: [
        {
          name: "直接访问",
          type: "bar",
          barWidth: "60%",
          data: [10, 52, 200, 334, 390, 330, 220]
        }
      ]
    });
    myChart.on("click", function(param) {
      alert("进入" + param.name + "项目");
      that.$router.push("/Main/ViewProject");
    });
  }
};
</script>

<style lang="less" scoped>
</style>