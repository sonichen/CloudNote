<template>
  <div>
    <h2 style="color: #999;">
      我的收藏
    </h2>
    <el-table
      :data="currentNotes"
      border
      style="max-height: calc(100vh - 100px); overflow: auto;"
      v-loading="loading"
    >
      <!-- 序号 -->
      <el-table-column
        fixed
        type="index"
        label="序号"
        align="center"
        width="80"
      />
      <el-table-column
        fixed
        prop="title"
        label="标题"
      />
      <el-table-column
        fixed
        prop="title"
        label="文件类型"
      >
        <template slot-scope="scope">
          {{ scope.row.mode+''==='1'?'富文本':'MarkDown' }}
        </template>
      </el-table-column>
      <el-table-column
        fixed
        prop="createTime"
        label="创建时间"
      />
      <el-table-column
        fixed
        prop="updateTime"
        label="更新时间"
      />
      <!-- <el-table-column
        fixed
        prop="version"
        label="版本"
      /> -->
      <el-table-column
        fixed="right"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            @click="handleCheck(scope.row.content,scope.row.mode)"
            type="text"
            size="small"
          >
            查看
          </el-button>
          <!-- <el-button
            type="text"
            size="small"
          >
            取消收藏
          </el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      style="right: 0; margin-top: 30px; text-align: right;"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="12"
      layout="total, prev, pager, next, jumper"
      :total="notes.length"
      background
    />
    <Check
      ref="checkCompo"
      :content="content"
      :mode="mode"
    />
  </div>
</template>

<script>
import { getStarList } from '@/api/star'
import Check from './Check.vue'
export default {
  components: { Check },
  data () {
    return {
      notes: [],
      content: '',
      mode: '',
      currentPage: 1
    }
  },
  computed: {
    currentNotes () {
      // size
      const PAGE_SIZE = 12
      return this.notes.slice((this.currentPage - 1) * PAGE_SIZE, this.currentPage * PAGE_SIZE)
    }
  },
  mounted () {
    this.getStarList()
  },
  methods: {
    getStarList () {
      this.loading = true
      getStarList().then(res => {
        this.notes = res.data.data
        this.loading = false
      })
    },
    handleCheck (content, mode) {
      this.content = content
      this.mode = mode + ''
      this.$refs.checkCompo.openDialog()
    },
    handleCurrentChange (page) {
      this.currentPage = page
    }
  }
}
</script>

<style lang="scss" scoped>
//
</style>
