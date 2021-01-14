import html2canvas from 'html2canvas'
import JsPDF from 'jspdf'

/**
 * @param  ele          要生成 pdf 的DOM元素（容器）
 * @param  padfName     PDF文件生成后的文件名字
 * */

function downloadPDF (ele, pdfName) {
  const eleW = ele.offsetWidth// 获得该容器的宽
  const eleH = ele.offsetHeight// 获得该容器的高

  const eleOffsetTop = ele.offsetTop// 获得该容器到文档顶部的距离
  const eleOffsetLeft = ele.offsetLeft// 获得该容器到文档最左的距离

  const canvas = document.createElement('canvas')
  let abs = 0

  const winIn = document.documentElement.clientWidth || document.body.clientWidth// 获得当前可视窗口的宽度（不包含滚动条）
  const winOut = window.innerWidth// 获得当前窗口的宽度（包含滚动条）

  if (winOut > winIn) {
    abs = (winOut - winIn) / 2// 获得滚动条宽度的一半
  }

  canvas.width = eleW * 2// 将画布宽&&高放大两倍
  canvas.height = eleH * 2

  const context = canvas.getContext('2d')

  context.scale(2, 2)

  context.translate(-eleOffsetLeft - abs, -eleOffsetTop)
  // 这里默认横向没有滚动条的情况，因为offset.left(),有无滚动条的时候存在差值，因此
  // translate的时候，要把这个差值去掉

  // html2canvas(element).then( (canvas)=>{ //报错
  // html2canvas(element[0]).then( (canvas)=>{
  html2canvas(ele, {
    dpi: 300,
    // allowTaint: true,  //允许 canvas 污染， allowTaint参数要去掉，否则是无法通过toDataURL导出canvas数据的
    useCORS: true
  }).then((canvas) => {
    const contentWidth = canvas.width
    const contentHeight = canvas.height
    const pageHeight = contentWidth / 592.28 * 841.89
    let leftHeight = contentHeight
    let position = 0
    const imgWidth = 595.28
    const imgHeight = 595.28 / contentWidth * contentHeight
    const pageData = canvas.toDataURL('image/jpeg', 1.0)

    const pdf = new JsPDF('', 'pt', 'a4')

    if (leftHeight < pageHeight) {
      pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
    } else {
      while (leftHeight > 0) {
        pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
        leftHeight -= pageHeight
        position -= 841.89
        if (leftHeight > 0) {
          pdf.addPage()
        }
      }
    }

    pdf.save(pdfName)
  })
}

export default {
  downloadPDF
}
