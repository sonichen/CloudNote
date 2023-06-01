export const offsetDomTop = (element) => {
  var offest = {
    top: 0
  }

  var _position

  getOffset(element, true)

  return offest

  // 递归获取 offset, 可以考虑使用 getBoundingClientRect
  function getOffset (node, init) {
    // 非Element 终止递归
    if (node.nodeType !== 1) {
      return
    }
    _position = window.getComputedStyle(node).position

    // position=static: 继续递归父节点
    if (typeof (init) === 'undefined' && _position === 'static') {
      getOffset(node.parentNode)
      return
    }
    offest.top = node.offsetTop + offest.top - node.scrollTop

    // position = fixed: 获取值后退出递归
    if (_position === 'fixed') {
      return
    }

    getOffset(node.parentNode)
  }
}

// 实现jquery.animate方法对scrollTop的实现
export const scrolltoToc = (position) => {
  // scrollTop:设置或获取位于对象最顶端和窗口中可见内容的最顶端之间的距离
  let currTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop
  const timeId = setInterval(() => {
    currTop += 10

    // 网页被卷去的高： document.body.scrollTop;
    document.body.scollTop = currTop

    // document.documentElement.scrollTop(获取或设置滚动条位置)
    document.documentElement.scrollTop = currTop

    if (currTop >= position) {
      clearInterval(timeId)
    }
  }, 10)
}
