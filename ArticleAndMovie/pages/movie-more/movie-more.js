// pages/movie-more/movie-more.js
var app = getApp();
var utils = require('../../utils/util.js');
Page({

  data: {
    totalCount: 0,
  },

  onLoad: function(options) {
    var categoryName = options.categoryName;
    var currentUrl = '';
    switch (categoryName) {
      case "正在热映":
        currentUrl = 'in_theaters';
        break;
      case "即将上映":
        currentUrl = 'coming_soon';
        break;
      case "排行榜":
        currentUrl = 'top250';
        break;
    };
    currentUrl = app.globalData.douBanBaseUrl + currentUrl + '?apikey=' + app.globalData.douBanApiKey;
    this.setData({
      currentUrl: currentUrl,
    });
    wx.setNavigationBarTitle({
      title: categoryName
    });

    utils.https(currentUrl, this.callback);
    wx.showLoading({
      title: '正在玩命加载中...',
    })
  },

  //下拉刷新
  onPullDownRefresh: function() {
    this.data.movies = {};
    this.data.totalCount = 0;
    utils.https(this.data.currentUrl, this.callback);
  },

  //上拉加载
  onReachBottom: function(event) {
    var currentUrl = this.data.currentUrl + '&start=' + this.data.totalCount + "&count=20";
    utils.https(currentUrl, this.callback);
    wx.showLoading({
      title: '正在玩命加载中...',
    })
  },

  callback: function(res) {
    var movies = [];
    for (var id in res.subjects) {
      var subject = res.subjects[id];
      // var title = subject.title;
      // if (title.length >= 6) {
      //   title = title.substring(0, 6) + "...";
      // };

      var temp = {
        title: utils.cutString(subject.title,0,6),
        largeImage: subject.images.large,
        starArr: utils.convertToStarArray(subject.rating.stars),
        average: subject.rating.average,
        movieId: subject.id,
      };
      movies.push(temp);
    };

    console.log(movies);
    var totalMovies = [];
    if (this.data.totalCount == 0) {
      totalMovies = movies;
    } else {
      totalMovies = this.data.movies.concat(movies);
    }

    this.setData({
      movies: totalMovies
    });
    this.data.totalCount += 20;
    wx.hideLoading();
  },

  tapToMovieDetail: function(event) {
    wx: wx.navigateTo({
      url: '../movie-detail/movie-detail?movieId=' + event.currentTarget.dataset.movieid,
    })
  },


})