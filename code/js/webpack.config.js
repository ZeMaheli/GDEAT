const path = require('path')

module.exports = {
    mode: 'development',
    resolve: {
        extensions: [".js", ".ts", ".tsx", ".css", ".ico"]
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif|ico)$/,
                type: "asset/resource",
                use: ['file-loader?name=[name].[ext]']
            },
        ],
    },

    devServer: {
        historyApiFallback: true,
        port: 9000,
        static: path.resolve(__dirname, 'dist')
    },
};
