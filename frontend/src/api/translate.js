import { request } from '@/utils/request'
/**
 * 获取翻译结果
 */
export function translate (q, from, to, appid, salt, sign) {
  return request({
    url: '/trans/vip/translate',
    method: 'GET',
    params: {
      q, // 待翻译的文本
      from, // 源语言
      to, // 翻译为
      appid,
      salt, // 随机数
      sign
    }
  })
}
