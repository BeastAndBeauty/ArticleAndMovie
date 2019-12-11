// pages/news-detail/news-detail.js
var newsData = require("../datas/news-data.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {},

  onLoad: function(options) {
    this.setData(newsData.newsData[options.newsId]);
    this.setData({
      newsId: options.newsId,
    });

    //收藏存储数据格式
    /**
     * var newsCollections={
     * 0:false,
     * 1:true,
     * 2:false,
     * 4:false
     * }
     */

    //第一次进入的时候判断是否存在本地存储以及是否收藏

    var newsCollections = wx.getStorageSync("newsCollections");

    //如果newsCollction存在，则代表以前收藏或者是以前取消过收藏
    if (newsCollections) {
      var newsCollection = newsCollections[options.newsId];
      this.setData({
        collected: newsCollection
      })
    } else {
      //第一次进入，根本不存在数据
      var newsCollections = {};
      //把当前唯一id扔到newsCollections对象中，然后默认指定false
      newsCollections[options.newsId] = false;
      //扔到本地存储去
      wx.setStorageSync('newsCollections', newsCollections);

    }
  },

  collectionTap: function(event) {
    var newsCollections = wx.getStorageSync('newsCollections');
    var newsCollection = newsCollections[this.data.newsId];
    newsCollection = !newsCollection;
    newsCollections[this.data.newsId] = newsCollection;
    wx.setStorageSync('newsCollections', newsCollections);
    this.setData({
      collected: newsCollection,
    });
    wx: wx.showToast({
      title: newsCollections[this.data.newsId] ? '收藏成功' : '取消收藏',
      icon: 'success',
      image: '',
      duration: 800,
      mask: true,
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})