<template>
  <div class="table_container">
    <h2 style="color: #999;">
      笔记管理
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
      >
        <template slot-scope="scope">
          <el-input
            v-if="isEditing&&editId===scope.row.id"
            v-model="editName"
            placeholder="笔记本名称"
            style="width: 40%;"
            @keyup.enter.native="blur"
            @blur="renameNote(0,scope.row.id,scope.row.name)"
            :disabled="scope.row.id===0"
            v-focus
          />
          <span
            @dblclick="dbclick(0,scope.row.id,scope.row.name)"
            v-else
            style="cursor: pointer;"
          >
            {{ scope.row.name }}
          </span>
        </template>
      </el-table-column>
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
            <el-input
              v-if="isEditing&&editId===item.id"
              v-model="editName"
              placeholder="笔记名称"
              style="width: 80%;"
              @keyup.enter.native="blur"
              @blur="renameNote(1,item.id,item.title)"
              v-focus
            />
            <span
              @dblclick="dbclick(1,item.id,item.title)"
              v-else
              style="cursor: pointer;"
            >
              {{ item.title }}
            </span>
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
              icon="el-icon-rank"
              @click="UpdateMenu(1,item.id)"
            >
              移动
            </el-button>
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="DeleteNote(item.id)"
            >
              删除
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
          <el-popover
            placement="bottom"
            width="100"
            trigger="click"
          >
            <el-dropdown-item
              @click.native="CreateMenu(0,row)"
            >
              <i class="el-icon-document-copy" />
              创建笔记本
            </el-dropdown-item>
            <el-dropdown-item
              v-if="row.id!==0"
              @click.native="CreateMenu(1,row)"
            >
              <i class="el-icon-tickets" />
              添加笔记
            </el-dropdown-item>
            <el-button
              slot="reference"
              size="mini"
              icon="el-icon-plus"
              style="margin-left: 10px;"
            >
              新建
            </el-button>
          </el-popover>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="DeleteMenu(row)"
            style="margin-left: 10px;"
            :disabled="row.id===0"
          >
            删除
          </el-button>
          <el-button
            size="mini"
            icon="el-icon-rank"
            @click="UpdateMenu(0,row.id)"
            :disabled="row.id===0"
          >
            移动
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <Add
      ref="addCompo"
      :add-config="addConfig"
      @Refresh="Refresh"
    />
    <Move
      ref="moveCompo"
      @Refresh="Refresh"
      :move-config="moveConfig"
      :data="NoteTreeList"
    />
  </div>
</template>

<script>
import Add from './components/Add'
import Move from './components/Move'
import { getNoteBookList, deleteNoteBook, renameNoteBook } from '@/api/noteBook'
import { deleteNote, renameNote } from '@/api/note.js'
export default {
  components: { Add, Move },
  data () {
    return {
      NoteTreeList: [], // 系统菜单列表
      addConfig: {
        // head: 0, // 头的id
        // isNoteBook: true // 是否为笔记本
      },
      moveConfig: {
        // id:0 // 目标笔记或笔记本id
        // isNoteBook: true // 是否为笔记本
      },
      isEditing: false,
      editName: '',
      editId: undefined,
      loading: false
    }
  },
  mounted () {
    this.getMenu()
  },
  methods: {
    getMenu () {
      this.loading = true
      getNoteBookList().then(res => {
        console.log(res.data.data, 'res.data.data')
        this.NoteTreeList = [{ id: 0, name: '根目录', children: res.data.data }]
        this.loading = false
      })
    },
    UpdateMenu (index, id) {
      // 0：表示移动笔记本，1：表示移动笔记
      this.moveConfig = {
        id: id,
        isNoteBook: index === 0
      }
      this.$refs.moveCompo.openDialog()
    },
    DeleteNote (id) {
      this.$confirm('确定删除吗？删除后可以在回收站恢复。')
        .then(_ => {
          deleteNote(id).then(res => {
            this.changeMenu()
            this.$message({
              type: 'success',
              message: '成功删除笔记'
            })
            this.Refresh()
          })
        })
        .catch(_ => {})
    },
    DeleteMenu (row) {
      this.$confirm('确定删除吗？删除后可以在回收站恢复。')
        .then(_ => {
          deleteNoteBook(row.id).then(res => {
            this.changeMenu()
            this.$message({
              type: 'success',
              message: '成功删除笔记本'
            })
            this.Refresh()
          })
        })
        .catch(_ => {})
    },
    CreateMenu (index, row) {
      // 0：表示创建笔记本，1：表示创建笔记
      console.log(index, row)
      // head => row.id ,[name]
      this.addConfig = {
        head: row.id,
        isNoteBook: index === 0
      }
      console.log(this.addConfig)
      this.$refs.addCompo.openDialog()
    },
    Refresh () {
      this.getMenu()
    },
    // 双击事件
    dbclick (index, id, name) {
      // 0为修改笔记本名，1为修改笔记名
      console.log(id)
      this.isEditing = true
      this.editName = name
      this.editId = id
    },
    // 失去焦点
    blur (e) {
      e.srcElement.blur() // 让输入框主动失焦
    },
    // 更改笔记（本）名字
    renameNote (index, id, name) {
      // name 为改变前的值
      this.isEditing = false
      if (name !== this.editName) {
        // console.log('blur', id, this.editName)
        if (index === 0) {
          // 笔记本
          renameNoteBook(id, this.editName).then(res => {
            this.changeMenu()
            this.$message({
              type: 'success',
              message: '成功修改笔记本'
            })
            this.Refresh()
          })
        } else {
          // 笔记
          renameNote(id, this.editName).then(res => {
            this.changeMenu()
            this.$message({
              type: 'success',
              message: '成功修改笔记'
            })
            this.Refresh()
          })
        }
      }
    }
  },
  directives: {
    focus: {
      inserted (el) {
        el.querySelector('input').focus()
      }
    }
  }
}
</script>

<style scoped lang="scss">
.table_container {
  // padding: 2px;
  // background-color: #efefef;
}

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
