<template>
  <div class="table_container">
    <h2 style="color: #999;">
      回收站
    </h2>
    <el-table
      :data="NoteTreeList"
      row-key="id"
      style="max-height: calc(100vh - 160px); overflow: auto;"
      border
      :indent="24"
      :tree-props="{ children: 'children'}"
      :expand-row-keys="['0','1', '2','3','4','5','6']"
      v-loading="loading"
    >
      <!-- 笔记本名称 -->
      <el-table-column
        prop="name"
        label="笔记本名称"
        show-overflow-tooltip
        min-width="180px"
      />
      <!-- 笔记本id -->
      <el-table-column
        prop="id"
        label="笔记本ID"
        show-overflow-tooltip
        align="center"
      />
      <!-- 含有的笔记名称 -->
      <el-table-column
        class="notes_container"
        label="包含的笔记名称"
        show-overflow-tooltip
        min-width="140px"
        min-height="90px"
      >
        <template slot-scope="scope">
          <div
            class="note_title_container"
            v-for="(item,index) in scope.row.contentList"
            :key="index"
          >
            {{ item.title }}
          </div>
        </template>
      </el-table-column>
      <!-- 含有的笔记id -->
      <el-table-column
        class="notes_container"
        label="包含的笔记ID"
        show-overflow-tooltip
        min-width="90px"
        min-height="90px"
      >
        <template slot-scope="scope">
          <div
            class="note_title_container"
            v-for="(item,index) in scope.row.contentList"
            :key="index"
          >
            {{ item.id }}
          </div>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column
        class="notes_container"
        label="操作"
        align="center"
        show-overflow-tooltip
        min-width="180px"
        min-height="90px"
      >
        <template slot-scope="scope">
          <div
            class="note_title_container"
            v-for="(item,index) in scope.row.contentList"
            :key="index"
          >
            <el-button
              size="mini"
              icon="el-icon-refresh-right"
              @click="recover(1,item.id)"
            >
              恢复
            </el-button>
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="deleteF(1,item.id)"
            >
              永久删除
            </el-button>
          </div>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column
        label="操作"
        align="center"
        min-width="180px"
      >
        <template #default="{row}">
          <el-button
            size="mini"
            icon="el-icon-refresh-right"
            @click="recover(0,row.id)"
            style="margin-left: 10px;"
            :disabled="row.id===0"
          >
            恢复
          </el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="deleteF(0,row.id)"
            style="margin-left: 10px;"
            :disabled="row.id===0"
          >
            永久删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getBinList, recoverNoteBook, recoverNote, deleteNoteBook, deleteNote } from '@/api/bin'
export default {
  data () {
    return {
      NoteTreeList: [] // 系统菜单列表
    }
  },
  mounted () {
    this.getMenu()
  },
  methods: {
    getMenu () {
      this.loading = true
      getBinList().then(res => {
        console.log(res.data.data, 'res.data.data')
        this.NoteTreeList = [{ id: 0, name: '根目录', children: res.data.data }]
        this.loading = false
      })
    },
    // 恢复笔记（本）
    recover (index, id) {
      if (index === 0) {
        // 笔记本
        recoverNoteBook(id).then(res => {
          this.changeMenu()
          this.$message({
            type: 'success',
            message: '成功恢复笔记本'
          })
          this.getMenu()
        })
      } else {
        // 笔记
        recoverNote(id).then(res => {
          this.changeMenu()
          this.$message({
            type: 'success',
            message: '成功恢复笔记'
          })
          this.getMenu()
        })
      }
    },
    // 永久删除笔记（本）
    deleteF (index, id) {
      if (index === 0) {
        // 笔记本
        this.$confirm('确定永久删除吗？')
          .then(_ => {
            deleteNoteBook(id).then(res => {
              this.$message({
                type: 'success',
                message: '彻底删除笔记本'
              })
              this.getMenu()
            })
          })
          .catch(_ => {})
      } else {
        // 笔记
        this.$confirm('确定永久删除吗？')
          .then(_ => {
            deleteNote(id).then(res => {
              this.$message({
                type: 'success',
                message: '彻底删除笔记'
              })
              this.getMenu()
            })
          })
          .catch(_ => {})
      }
    }
  }
}
</script>

<style scoped lang="scss">

.note_title_container {
  padding: 15px 20px;

  &:not(:first-child) {
    border-top: 1px solid #ebeef5;
  }

  &:first-child {
    height: 50px;
    padding-top: 0;
    padding-bottom: 12px;
  }

  &:last-child {
    height: 50px;
    padding-top: 12px;
    padding-bottom: 0;
  }

  &:not(:first-child):not(:last-child) {
    height: 50px;
    padding-top: 12px;
    padding-bottom: 12px;
  }
}

::v-deep .cell.el-tooltip {
  padding: 0;
}

::v-deep .el-table::before {
  display: none;
}
</style>
