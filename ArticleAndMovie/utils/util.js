const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 分值转换为小星星
 */
function convertToStarArray(stars) {
  var num = stars.substring(0, 1);
  var starArr = [];
  for (var i = 0; i < 5; i++) {
    if (i < num) {
      starArr.push(1);
    } else {
      starArr.push(0);
    }
  }
  return starArr;
}

function https(url, callback) {
  wx.request({
    url: url,
    method: 'GET',
    header: {
      'content-type': 'application/xml'
    },
    success: function(res) {
      callback(res.data);
    }
  })
}

//演员名字使用'/'分割开
function convertToCastString(casts) {
  var castStr = '';
  for (var id in casts) {
    castStr += casts[id].name + " / ";
  }
  return castStr.substring(0, castStr.length - 3);
}

//处理演员信息：头像+名字
function convertToCastsInfoArray(casts) {
  var castsArray = [];
  for (var id in casts) {
    var cast = {
      img: casts[id].avatars ? casts[id].avatars.large : "",
      name: casts[id].name
    }
    castsArray.push(cast);
  }
  return castsArray;
}

//截取字符串长度
function cutString(str,start,end){
  if (str.length>end){
    str = str.substring(start,end)+'...';
  }
  return str;
}

module.exports = {
  formatTime: formatTime,
  convertToStarArray: convertToStarArray,
  https: https,
  convertToCastString: convertToCastString,
  convertToCastsInfoArray: convertToCastsInfoArray,
  cutString: cutString
}