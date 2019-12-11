// pages/movie-detail/movie-detail.js
var app = getApp();
var utils = require('../../utils/util.js');

Page({
  data: {

  },

  onLoad: function(options) {

  

    var detailUrl = app.globalData.douBanBaseUrl + 'subject/' + options.movieId + '?apikey=' + app.globalData.douBanApiKey;
    utils.https(detailUrl, this.callback);

  },

  callback: function(res) {
    console.log(res);

    var director = {
      avatar: '',
      name: '',
      id: '',
    };
    if (res.directors[0] != null) {
      if (res.directors[0].avatars != null) {
        director.avatar = res.directors[0].avatars.large;
      }
      director.name = res.directors[0].name;
      director.id = res.directors[0].id;
    }

    var temp = {
      movieImage: res.images.large,
      country: res.countries[0],
      title: res.title,
      originalTitle: res.original_title,
      wishCount: res.wish_count,
      commentCount:res.comments_count,
      year: res.year,
      generes: res.genres,
      stars: utils.convertToStarArray(res.rating.stars),
      score: res.rating.average,
      director: director,
      casts: utils.convertToCastString(res.casts),
      castsInfo: utils.convertToCastsInfoArray(res.casts),
      summary: res.summary
    };
    console.log(temp);
    this.setData({
      movie:temp
    });

    wx.setNavigationBarTitle({
      title: utils.cutString(this.data.movie.title, 0, 6),
    })

  },
 
})