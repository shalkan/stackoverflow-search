var webpack = require('webpack');
var path = require('path');
// const HtmlWebpackPlugin = require('html-webpack-plugin')


module.exports = {
  entry: {
    'index': './src/index-main.js'
  },
  output: {
    path: __dirname + '/resources',
    filename: '../../static/js/[name].js',
  },
  module: {
    loaders: [
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        query: {
          presets:[ 'es2015', 'stage-2' ],
          plugins: ['transform-runtime', 'syntax-decorators', 'transform-object-rest-spread',
            new webpack.NormalModuleReplacementPlugin(/element-ui[\/\\]lib[\/\\]locale[\/\\]lang[\/\\]ru-RU/, 'element-ui/lib/locale/lang/en')]
        }
      },
      {
        test: /\.css$/,
        loader: "style-loader!css-loader",
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        query: {
          presets:[ 'es2015', 'stage-2' ]
        }
      },
      {
        test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
        loader: 'file-loader'
      },
      {
        test: /\.(png|jpe?g|gif|svg)(\?\S*)?$/,
        loader: 'file-loader',
        query: {
          name: '[name].[ext]?[hash]'
        }
      }
    ]
  },
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.common.js',
      "request$": "xhr"
    }
  }
}