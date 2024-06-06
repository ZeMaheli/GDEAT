const path = require('path')

module.exports = {
    mode: 'development',
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
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
                test: /\.(png|jp(e*)g|svg|gif)$/,
                type: "asset/resource",
            },
        ],
    },

    devServer: {
        historyApiFallback: true,
        proxy: {
            '/api': {
                target: 'http://localhost:9000',
                router: () => 'http://localhost:8080',
            }
        },
        port: 9000,
        static: path.resolve(__dirname, 'dist')
    },
};
