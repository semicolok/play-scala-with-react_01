var path = require('path');
var webpack = require('webpack');

const COMPONENTS_PATH = __dirname + '/src';

module.exports = {
  entry: COMPONENTS_PATH + '/main.js',
  output: {
    path: __dirname, 
    filename: 'bundle.js' 
  },
  module: {
    loaders: [
      {
        test: /.jsx?$/,
        loaders: ['react-hot', 'babel-loader?presets[]=es2015&presets[]=react&presets[]=stage-0'],
        include: COMPONENTS_PATH,
        exclude: /node_modules/
      }
    ]
  },
};
