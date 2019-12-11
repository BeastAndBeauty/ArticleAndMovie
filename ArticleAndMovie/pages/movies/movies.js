// pages/movies/movies.js
var app = getApp();
var convertToStarArray = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // containerShow: true,
    // searchPanelShow: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(app.globalData.douBanBaseUrl);
    var inThreaters = app.globalData.douBanBaseUrl + 'in_theaters? count =3' + "&apikey=" + app.globalData.douBanApiKey;
    var comingSoon = app.globalData.douBanBaseUrl + 'coming_soon? count =3' + "&apikey=" + app.globalData.douBanApiKey;
    var top250 = app.globalData.douBanBaseUrl + 'top250? count =3' + "&apikey=" + app.globalData.douBanApiKey;
    this.https(inThreaters, 'inThreaters', '正在热映', this.callback);
    this.https(comingSoon, 'comingSoon', '即将上映', this.callback);
    this.https(top250, 'top250', '排行榜', this.callback);

  },

  https: function(url, category, categoryName, callback) {
    wx: wx.request({
      url: url,
      data: '',
      header: {
        'content-type': 'application/xml'
      },
      method: 'GET',
      success: function(res) {
        callback(res.data, category, categoryName);
      },
      fail: function(res) {},
      complete: function(res, category) {},
    });
  },

  callback: function(res, category, categoryName) {
    var movies = [];
    for (var id in res.subjects) {
      var subject = res.subjects[id];
      var title = subject.title;
      if (title.length >= 6) {
        title = title.substring(0, 6) + "...";
      };

      var temp = {
        title: title,
        largeImage: subject.images.large,
        starArr: convertToStarArray.convertToStarArray(subject.rating.stars),
        average: subject.rating.average,
        movieId: subject.id,
      };
      movies.push(temp);
    };
    var readyData = {};
    readyData[category] = {
      categoryName: categoryName,
      movies: movies
    };
    this.setData(readyData);
    console.log(readyData);
  },

  moreTapToMovieMore: function(event) {
    var categoryName = event.currentTarget.dataset.categoryname;
    wx: wx.navigateTo({
      url: '../movie-more/movie-more?categoryName=' + categoryName,
    })
  },

  tapToMovieDetail: function(event) {
    wx: wx.navigateTo({
      url: '../movie-detail/movie-detail?movieId=' + event.currentTarget.dataset.movieid,
    })
  },

  // onBindFoucs: function(event) {
  //   this.setData({
  //     containerShow: false,
  //     searchPanelShow: true
  //   })
  // },

  // onBindBlur: function(event) {
  //   this.setData({
  //     containerShow: true,
  //     searchPanelShow: false
  //   });
    
  //   var text = event.detail.value;
  //   var searchUrl = app.globalData.douBanBaseUrl + 'search?q=' + text + '&apikey=' + app.globalData.douBanApiKey;
  //   console.log(searchUrl);
  //   this.https(searchUrl, 'searchData', '', this.callback)
  // },

  // onCancelTap: function(event) {
  //   this.setData({
  //     containerShow: true,
  //     searchPanelShow: false
  //   })
  // },
})