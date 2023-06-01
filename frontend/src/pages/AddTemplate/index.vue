<template>
  <div>
    <DialogCard
      ref="dialog"
      title="添加模板"
    >
      <el-form
        label-position="top"
      >
        <el-form-item label="笔记名称：">
          <el-input
            v-model="noteContent.title"
            disabled
          />
        </el-form-item>
        <el-form-item label="系统模板：">
          <div class="template_wraper">
            <div
              class="template_item"
              v-for="item in systemTemplates"
              :key="item.id"
            >
              <el-tooltip
                class="item"
                effect="light"
                :content="item.title"
                placement="top"
              >
                <div class="template_item_title">
                  {{ item.title }}
                </div>
              </el-tooltip>
              <div class="template_item_tools">
                <el-tooltip
                  class="item"
                  effect="light"
                  content="使用"
                  placement="bottom"
                >
                  <el-button
                    icon="el-icon-circle-check"
                    type="primary"
                    size="mini"
                    circle
                    @click="handleUse(item)"
                    :disabled="!noteContent.id"
                  />
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="light"
                  content="编辑"
                  placement="bottom"
                >
                  <el-button
                    icon="el-icon-edit"
                    circle
                    size="mini"
                    @click="handleEdit(item)"
                  />
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="light"
                  content="删除"
                  placement="bottom"
                >
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    size="mini"
                    @click="handleDelete(item)"
                  />
                </el-tooltip>
              </div>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="我的模板：">
          <div class="template_wraper">
            <div
              class="template_item"
              v-for="item in myTemplates"
              :key="item.id"
            >
              <el-tooltip
                class="item"
                effect="light"
                :content="item.title"
                placement="top"
              >
                <div class="template_item_title">
                  {{ item.title }}
                </div>
              </el-tooltip>
              <div class="template_item_tools">
                <el-tooltip
                  class="item"
                  effect="light"
                  content="使用"
                  placement="bottom"
                >
                  <el-button
                    icon="el-icon-circle-check"
                    type="primary"
                    size="mini"
                    circle
                    @click="handleUse(item)"
                    :disabled="!noteContent.id"
                  />
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="light"
                  content="编辑"
                  placement="bottom"
                >
                  <el-button
                    icon="el-icon-edit"
                    circle
                    size="mini"
                    @click="handleEdit(item)"
                  />
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="light"
                  content="删除"
                  placement="bottom"
                >
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    size="mini"
                    @click="handleDelete(item)"
                  />
                </el-tooltip>
              </div>
            </div>
            <div
              class="template_item"
              @click="handleAdd"
            >
              <div class="template_item_title">
                添加模板
              </div>
              <div class="template_item_tools">
                <i class="el-icon-plus" />
              </div>
            </div>
          </div>
        </el-form-item>
      </el-form>
    </DialogCard>
    <EditOrCreate
      ref="manipulateTemplate"
      :item="manipulateTemplateItem"
      @refresh="findTemplates"
    />
  </div>
</template>

<script>
import DialogCard from '@/components/DialogCard'
import EditOrCreate from './EditOrCreate.vue'
import { mapState, mapMutations } from 'vuex'
import { findMyTemplates, findSystemTemplates, deleteTemplate } from '@/api/add_template'
export default {
  name: 'TemplateManage',
  components: { DialogCard, EditOrCreate },
  data () {
    return {
      myTemplates: [],
      systemTemplates: [],
      manipulateTemplateItem: {} // flag:0+,1编辑
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  methods: {
    ...mapMutations(['setNoteContent']),
    openDialog () {
      this.$refs.dialog.dialogVisible = true
      this.findTemplates()
    },
    closeDialog () {
      this.$refs.dialog.dialogVisible = false
    },
    async findTemplates () {
      const { data } = await findMyTemplates()
      this.myTemplates = data.data
      const res = await findSystemTemplates()
      this.systemTemplates = res.data.data
      console.log(this.myTemplates)
    },
    async handleUse (item) {
      await this.$confirm('确认使用该模板？使用模板将会覆盖当前笔记内容！')
      this.setNoteContent({ ...this.noteContent, content: item.content })
      this.closeDialog()
    },
    handleEdit (item) {
      this.$refs.manipulateTemplate.openDialog()
      this.manipulateTemplateItem = { ...item, flag: 1 }
    },
    async handleDelete (item) {
      await this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
      await deleteTemplate(item.id)
      this.$message({
        type: 'success',
        message: '删除成功'
      })
      this.findTemplates()
    },
    handleAdd () {
      this.$refs.manipulateTemplate.openDialog()
      this.manipulateTemplateItem = { flag: 0 }
    }
  }
}
</script>

<style lang="scss" scoped>
.template_wraper {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.template_item {
  display: inline-block;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 170px;
  height: 100px;
  padding: 10px;
  margin: 5px;
  border: 1px solid #f4f4f4;
  border-radius: 16px;

  &:hover {
    background-color: rgb(244, 247, 255);
  }

  &_title {
    overflow: hidden;
    text-align: center;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  &_tools {
    width: fit-content;
    margin: auto;
  }
}
</style>
