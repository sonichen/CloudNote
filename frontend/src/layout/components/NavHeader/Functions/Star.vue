<template>
  <div>
    <el-tooltip
      class="item"
      effect="light"
      content="收藏"
      placement="bottom-end"
    >
      <el-button
        :icon="star==='0'?'el-icon-star-off':'el-icon-star-on'"
        circle
        type="info"
        plain
        @click="handleStar"
        :disabled="!noteContent.id"
      />
    </el-tooltip>
  </div>
</template>

<script>
import { starNote } from '@/api/note'
import { mapState, mapMutations } from 'vuex'

export default {
  data () {
    return {
      star: '0'
    }
  },
  computed: {
    ...mapState(['noteContent'])
  },
  watch: {
    noteContent: {
      handler (newV) {
        if (newV.star) {
          this.star = newV.star + ''
        }
      },
      deep: true
    }
  },
  methods: {
    ...mapMutations(['setNoteContent']),
    handleStar () {
      if (this.star === '0') {
        this.star = '1'
      } else if (this.star === '1') {
        this.star = '0'
      }
      starNote(this.noteContent.id, this.star).then(res => {
        this.$message({
          type: 'success',
          message: this.star === '1' ? '成功收藏笔记' : '成功取消收藏'
        })
        this.setNoteContent({ ...this.noteContent, star: this.star })
      })
    }
  }
}
</script>

<style scoped>
::v-deep [class*="el-icon-"] {
  font-size: 18px !important;
}
</style>
