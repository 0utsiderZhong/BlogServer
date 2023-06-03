import request from '@/utils/request'


// 查询本地图片
export function listLocalImage(query) {
  return request({
    url: '/tool/localStorage/list',
    method: 'get',
    params: query
  })
}

export function uploadLocalImage(data) {
  return request({
    url: '/tool/localStorage',
    method: 'post',
    params: data
  })
}

//上传图片
export function uploadImgToQiNiu(data) {
  return request({
    url: '/tool/qiNiu',
    method: 'post',
    data: data
  })
}
