<template>
  <div>
    <DialogCard
      ref="dialog"
      title="标签搜索"
    >
      <el-input
        placeholder="请输入标签"
        v-model="searchText"
        clearable
        style="margin-bottom: 10px;"
        @keyup.enter.native="getList"
      >
        <i
          slot="suffix"
          class="el-input__icon el-icon-search"
          @click="getList"
        />
      </el-input>
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
        <el-table-column
          fixed
          prop="categoriesName"
          label="所属笔记本"
        />
        <el-table-column
          fixed="right"
          label="操作"
          min-width="120px"
        >
          <template slot-scope="scope">
            <el-button
              @click="handleCheck(scope.row.content,scope.row.mode)"
              plain
              size="small"
            >
              查看
            </el-button>
            <el-button
              @click="handleJump(scope.row)"
              type="primary"
              size="small"
            >
              跳转
            </el-button>
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
      <el-row
        type="flex"
        justify="center"
        style="margin-top: 30px;"
      >
        <el-button
          size="small"
          type="primary"
          @click="closeDialog"
        >
          确认
        </el-button>
      </el-row>
    </DialogCard>
    <Check
      ref="checkCompo"
      :content="content"
      :mode="mode"
    />
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import { searchNotesByTag } from '@/api/search'
import Check from './Check.vue'
import { mapState, mapMutations } from 'vuex'
export default {
  components: { Check, DialogCard },
  data () {
    return {
      notes: [],
      content: '',
      mode: '',
      currentPage: 1,
      searchText: '',
      loading: false
    }
  },
  computed: {
    ...mapState(['noteContent']),
    currentNotes () {
      // size
      const PAGE_SIZE = 7
      return this.notes.slice((this.currentPage - 1) * PAGE_SIZE, this.currentPage * PAGE_SIZE)
    }
  },
  mounted () {

  },
  methods: {
    ...mapMutations(['setNoteContent']),
    async getList () {
      this.loading = true
      const { data } = await searchNotesByTag(this.searchText)
      this.notes = data.data
      this.loading = false
    },
    handleCheck (content, mode) {
      this.content = content
      this.mode = mode + ''
      this.$refs.checkCompo.openDialog()
    },
    handleCurrentChange (page) {
      this.currentPage = page
    },
    openDialog () {
      this.$refs.dialog.dialogVisible = true
      this.getList()
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    handleJump (row) {
      this.setNoteContent({ ...row })
      this.closeDialog()
      this.$router.push('/read-mode')
    }
  }
}
</script>

<style lang="scss" scoped>
//
</style>
