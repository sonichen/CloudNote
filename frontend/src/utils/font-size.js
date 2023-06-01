/*
 * @LineStart: -------------------------------------------
 * @Copyright: © 2020, itclanCoder. All rights reserved.
 * @LineEnd: ----------------------------------------------
 * @Product:
 * @Mode Name:
 * @Autor: ZQW
 * @Date: 2020-03-31 11:13:48
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-03-31 11:45:55
 * @Description:
 */
// 基准大小
const baseSize = 16

// 设置 rem 函数
function setRem () {
  let scale = document.documentElement.clientWidth / 1920
  if (document.documentElement.clientWidth < 1920) {
    // 当前页面宽度相对于 750 宽的缩放比例，可根据自己需要编辑。
    scale = document.documentElement.clientWidth / 1920
    // 设置页面根节点字体大小
    document.documentElement.style.fontSize =
      (baseSize * scale < 12 ? 12 : baseSize * scale) + 'px'
  } else {
    document.documentElement.style.fontSize =
      (baseSize * scale < 12 ? 12 : baseSize * scale) + 'px'
  }
}

function debounce (fn, wait = 50) {
  // 通过闭包缓存一个定时器 id
  let timer = null
  // 将 debounce 处理结果当作函数返回
  // 触发事件回调时执行这个返回函数
  return function (...args) {
    // 如果已经设定过定时器就清空上一次的定时器
    if (timer) clearTimeout(timer)
    // 开始设定一个新的定时器，定时器结束后执行传入的函数 fn
    timer = setTimeout(() => {
      fn.apply(this, args)
    }, wait)
  }
}
// 初始化
setRem()
// 改变窗口大小时重新设置 rem

window.onresize = () => {
  debounce(setRem(), 500)
}
