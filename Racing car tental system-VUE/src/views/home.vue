<template>
  <div class="dashboard-page">
    <section class="hero">
      <div>
        <p class="eyebrow">Operations overview</p>
        <h1>Racing car rental dashboard</h1>
        <p class="hero-copy">
          A quick view of fleet usage, order volume, and revenue trends from the live database.
        </p>
      </div>
      <div class="hero-note">
        <span class="hero-label">Current scope</span>
        <strong>{{ stats.activeRentals }}</strong>
        <span>cars currently in active rental</span>
      </div>
    </section>

    <section class="metric-grid">
      <article class="metric-card accent-revenue">
        <span class="metric-title">Revenue</span>
        <strong class="metric-value">${{ formatMoney(stats.totalRevenue) }}</strong>
        <span class="metric-note">Paid orders only</span>
      </article>

      <article class="metric-card accent-fleet">
        <span class="metric-title">Fleet size</span>
        <strong class="metric-value">{{ stats.totalCars }}</strong>
        <span class="metric-note">Cars listed in dispatch</span>
      </article>

      <article class="metric-card accent-orders">
        <span class="metric-title">Orders</span>
        <strong class="metric-value">{{ stats.totalOrders }}</strong>
        <span class="metric-note">Historical bookings</span>
      </article>

      <article class="metric-card accent-users">
        <span class="metric-title">Users</span>
        <strong class="metric-value">{{ stats.totalUsers }}</strong>
        <span class="metric-note">Registered customer accounts</span>
      </article>
    </section>

    <section class="panel-grid top-panels">
      <article class="panel panel-wide">
        <div class="panel-header">
          <div>
            <h2>Revenue trend</h2>
            <p>Monthly paid revenue over the last 12 months.</p>
          </div>
        </div>
        <div id="lineChart" class="chart-box"></div>
      </article>

      <article class="panel">
        <div class="panel-header">
          <div>
            <h2>Fleet status</h2>
            <p>Available cars versus rentals in progress.</p>
          </div>
        </div>
        <div id="pieChart" class="chart-box"></div>
      </article>
    </section>

    <section class="panel-grid bottom-panels">
      <article class="panel">
        <div class="panel-header">
          <div>
            <h2>Top rented cars</h2>
            <p>Most frequently booked cars in the order history.</p>
          </div>
        </div>
        <div id="barChart" class="chart-box"></div>
      </article>

      <article class="panel">
        <div class="panel-header">
          <div>
            <h2>Recent orders</h2>
            <p>Latest orders pulled from the database.</p>
          </div>
        </div>

        <div class="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Car</th>
                <th>Customer</th>
                <th>Amount</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in recentOrders" :key="order.id">
                <td>
                  <strong class="primary-text">{{ order.car_name }}</strong>
                  <span class="secondary-text">{{ order.brand }}</span>
                </td>
                <td>{{ order.real_name || order.username }}</td>
                <td class="amount-cell">${{ formatMoney(order.total_price) }}</td>
                <td>
                  <span :class="['status-pill', order.is_paid === 'Paid' ? 'paid' : 'unpaid']">
                    {{ order.is_paid }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </article>
    </section>
  </div>
</template>

<script>
import * as echarts from 'echarts'

const emptyStats = {
  totalRevenue: 0,
  totalCars: 0,
  totalOrders: 0,
  totalUsers: 0,
  activeRentals: 0
}

export default {
  name: 'DashboardHome',
  data() {
    return {
      stats: { ...emptyStats },
      recentOrders: [],
      lineChart: null,
      pieChart: null,
      barChart: null
    }
  },
  mounted() {
    this.checkAuth()
    this.loadDashboardData()
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    this.disposeCharts()
  },
  methods: {
    checkAuth() {
      const token = this.$storage.get('Token')
      if (!token) {
        this.$router.push({ name: 'login' })
        return
      }

      this.$http({
        url: `${this.$storage.get('sessionTable')}/session`,
        method: 'get'
      }).then(({ data }) => {
        if (data && data.code !== 0) {
          this.$router.push({ name: 'login' })
        }
      })
    },

    async loadDashboardData() {
      await Promise.all([
        this.loadStats(),
        this.loadLineChart(),
        this.loadPieChart(),
        this.loadTopCars(),
        this.loadRecentOrders()
      ])
    },

    async loadStats() {
      try {
        const incomeRes = await this.$http({ url: 'sys/stats/income', method: 'get' })
        if (incomeRes.data && incomeRes.data.code === 0) {
          this.stats.totalRevenue = incomeRes.data.data || 0
        }

        const countsRes = await this.$http({ url: 'sys/stats/counts', method: 'get' })
        if (countsRes.data && countsRes.data.code === 0) {
          const counts = countsRes.data.data || {}
          this.stats.totalUsers = counts.users || 0
          this.stats.totalCars = counts.cars || 0
          this.stats.totalOrders = counts.orders || 0
          this.stats.activeRentals = counts.activeRentals || 0
        }
      } catch (error) {
        this.stats = { ...emptyStats }
      }
    },

    async loadLineChart() {
      try {
        const response = await this.$http({ url: 'sys/stats/chart/line', method: 'get' })
        if (response.data && response.data.code === 0) {
          const chartData = response.data.data || {}
          this.initLineChart(chartData.months || [], chartData.revenues || [])
          return
        }
      } catch (error) {
        // keep the chart shell even when data loading fails
      }

      this.initLineChart([], [])
    },

    async loadPieChart() {
      try {
        const response = await this.$http({ url: 'sys/stats/chart/pie', method: 'get' })
        if (response.data && response.data.code === 0) {
          this.initPieChart(response.data.data || [])
          return
        }
      } catch (error) {
        // fallback handled below
      }

      this.initPieChart([])
    },

    async loadTopCars() {
      try {
        const response = await this.$http({ url: 'sys/stats/chart/top-cars', method: 'get' })
        if (response.data && response.data.code === 0) {
          this.initBarChart(response.data.data || [])
          return
        }
      } catch (error) {
        // fallback handled below
      }

      this.initBarChart([])
    },

    async loadRecentOrders() {
      try {
        const response = await this.$http({ url: 'sys/stats/recent-orders', method: 'get' })
        if (response.data && response.data.code === 0) {
          this.recentOrders = response.data.data || []
          return
        }
      } catch (error) {
        // fallback handled below
      }

      this.recentOrders = []
    },

    initLineChart(months, revenues) {
      const chartDom = document.getElementById('lineChart')
      if (!chartDom) {
        return
      }

      if (this.lineChart) {
        this.lineChart.dispose()
      }

      this.lineChart = echarts.init(chartDom)
      this.lineChart.setOption({
        tooltip: {
          trigger: 'axis',
          formatter: '{b}<br />Revenue: ${c}'
        },
        grid: {
          top: 24,
          left: 40,
          right: 18,
          bottom: 32
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: months,
          axisLine: { lineStyle: { color: '#cbd5e1' } },
          axisLabel: { color: '#64748b' }
        },
        yAxis: {
          type: 'value',
          axisLine: { show: false },
          axisLabel: {
            color: '#64748b',
            formatter: '${value}'
          },
          splitLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          }
        },
        series: [
          {
            name: 'Revenue',
            type: 'line',
            smooth: true,
            data: revenues,
            symbolSize: 8,
            lineStyle: {
              color: '#c2410c',
              width: 3
            },
            itemStyle: {
              color: '#c2410c'
            },
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: 'rgba(194, 65, 12, 0.24)' },
                { offset: 1, color: 'rgba(194, 65, 12, 0.02)' }
              ])
            }
          }
        ]
      })
    },

    initPieChart(data) {
      const chartDom = document.getElementById('pieChart')
      if (!chartDom) {
        return
      }

      if (this.pieChart) {
        this.pieChart.dispose()
      }

      this.pieChart = echarts.init(chartDom)
      this.pieChart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          bottom: 0,
          left: 'center',
          textStyle: {
            color: '#475569'
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['45%', '72%'],
            itemStyle: {
              borderColor: '#f8fafc',
              borderWidth: 4
            },
            label: {
              color: '#0f172a'
            },
            data: data.map((item, index) => ({
              ...item,
              itemStyle: {
                color: index === 0 ? '#0f766e' : '#ea580c'
              }
            }))
          }
        ]
      })
    },

    initBarChart(data) {
      const chartDom = document.getElementById('barChart')
      if (!chartDom) {
        return
      }

      if (this.barChart) {
        this.barChart.dispose()
      }

      this.barChart = echarts.init(chartDom)

      const carNames = data.map(item => {
        const name = item.car_name || ''
        return name.length > 18 ? `${name.slice(0, 18)}...` : name
      })

      const counts = data.map(item => item.rental_count || 0)

      this.barChart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: 18,
          left: 20,
          right: 20,
          bottom: 24,
          containLabel: true
        },
        xAxis: {
          type: 'value',
          axisLabel: { color: '#64748b' },
          splitLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          }
        },
        yAxis: {
          type: 'category',
          data: carNames,
          axisLabel: { color: '#475569' }
        },
        series: [
          {
            type: 'bar',
            data: counts,
            barWidth: '56%',
            itemStyle: {
              color: '#1d4ed8',
              borderRadius: [0, 8, 8, 0]
            }
          }
        ]
      })
    },

    handleResize() {
      if (this.lineChart) {
        this.lineChart.resize()
      }
      if (this.pieChart) {
        this.pieChart.resize()
      }
      if (this.barChart) {
        this.barChart.resize()
      }
    },

    disposeCharts() {
      if (this.lineChart) {
        this.lineChart.dispose()
        this.lineChart = null
      }
      if (this.pieChart) {
        this.pieChart.dispose()
        this.pieChart = null
      }
      if (this.barChart) {
        this.barChart.dispose()
        this.barChart = null
      }
    },

    formatMoney(value) {
      const numericValue = Number.parseFloat(value)
      if (Number.isNaN(numericValue) || numericValue <= 0) {
        return '0'
      }

      if (numericValue >= 1000000) {
        return `${(numericValue / 1000000).toFixed(2)}M`
      }

      if (numericValue >= 1000) {
        return `${(numericValue / 1000).toFixed(1)}K`
      }

      return numericValue.toFixed(0)
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-page {
  min-height: 100%;
  padding: 24px;
  background:
    radial-gradient(circle at top left, rgba(191, 219, 254, 0.55), transparent 26%),
    linear-gradient(180deg, #f8fafc 0%, #eef2f7 100%);
  color: #0f172a;
}

.hero {
  display: flex;
  justify-content: space-between;
  gap: 24px;
  padding: 28px 30px;
  margin-bottom: 20px;
  border: 1px solid rgba(148, 163, 184, 0.2);
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.92);
  box-shadow: 0 18px 45px rgba(15, 23, 42, 0.08);

  h1 {
    margin: 8px 0 10px;
    font-size: 32px;
    line-height: 1.1;
  }
}

.eyebrow {
  margin: 0;
  color: #b45309;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.14em;
  text-transform: uppercase;
}

.hero-copy {
  max-width: 640px;
  margin: 0;
  color: #475569;
  line-height: 1.6;
}

.hero-note {
  min-width: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 18px 20px;
  border-radius: 18px;
  background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
  color: #f8fafc;

  strong {
    margin: 6px 0;
    font-size: 36px;
    line-height: 1;
  }
}

.hero-label {
  color: #cbd5e1;
  font-size: 12px;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}

.metric-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 16px;
  margin-bottom: 20px;
}

.metric-card {
  display: flex;
  flex-direction: column;
  gap: 8px;
  padding: 22px;
  border-radius: 20px;
  border: 1px solid rgba(148, 163, 184, 0.18);
  background: rgba(255, 255, 255, 0.92);
  box-shadow: 0 14px 34px rgba(15, 23, 42, 0.06);
}

.metric-title {
  color: #64748b;
  font-size: 13px;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}

.metric-value {
  font-size: 30px;
  line-height: 1;
}

.metric-note {
  color: #475569;
  font-size: 13px;
}

.accent-revenue {
  border-top: 4px solid #c2410c;
}

.accent-fleet {
  border-top: 4px solid #0f766e;
}

.accent-orders {
  border-top: 4px solid #1d4ed8;
}

.accent-users {
  border-top: 4px solid #7c3aed;
}

.panel-grid {
  display: grid;
  gap: 18px;
}

.top-panels {
  grid-template-columns: 1.6fr 1fr;
  margin-bottom: 18px;
}

.bottom-panels {
  grid-template-columns: 1fr 1.2fr;
}

.panel {
  padding: 22px;
  border-radius: 24px;
  border: 1px solid rgba(148, 163, 184, 0.18);
  background: rgba(255, 255, 255, 0.92);
  box-shadow: 0 16px 38px rgba(15, 23, 42, 0.06);
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 16px;

  h2 {
    margin: 0 0 4px;
    font-size: 20px;
  }

  p {
    margin: 0;
    color: #64748b;
    font-size: 13px;
  }
}

.chart-box {
  height: 320px;
}

.table-wrap {
  max-height: 320px;
  overflow: auto;
}

table {
  width: 100%;
  border-collapse: collapse;

  th,
  td {
    padding: 14px 10px;
    text-align: left;
    border-bottom: 1px solid #e2e8f0;
  }

  th {
    color: #64748b;
    font-size: 12px;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.06em;
  }

  td {
    color: #0f172a;
    font-size: 14px;
  }
}

.primary-text {
  display: block;
  margin-bottom: 4px;
}

.secondary-text {
  display: block;
  color: #64748b;
  font-size: 12px;
}

.amount-cell {
  font-weight: 700;
  color: #0f766e;
}

.status-pill {
  display: inline-flex;
  align-items: center;
  padding: 6px 12px;
  border-radius: 999px;
  font-size: 12px;
  font-weight: 700;
}

.status-pill.paid {
  background: rgba(15, 118, 110, 0.12);
  color: #0f766e;
}

.status-pill.unpaid {
  background: rgba(234, 88, 12, 0.12);
  color: #c2410c;
}

@media (max-width: 1100px) {
  .metric-grid,
  .top-panels,
  .bottom-panels {
    grid-template-columns: 1fr 1fr;
  }

  .hero {
    flex-direction: column;
  }
}

@media (max-width: 760px) {
  .dashboard-page {
    padding: 16px;
  }

  .metric-grid,
  .top-panels,
  .bottom-panels {
    grid-template-columns: 1fr;
  }

  .hero {
    padding: 22px;

    h1 {
      font-size: 26px;
    }
  }

  .chart-box {
    height: 280px;
  }
}
</style>
