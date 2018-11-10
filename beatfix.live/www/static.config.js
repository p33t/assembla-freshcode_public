export default {
    getSiteData: () => ({
        title: 'beatfix.live'
    }),
    getRoutes: () => {
        return [
            {
                path: '/',
                component: 'src/containers/Home',
            },
            {
                path: '/features',
                component: 'src/containers/Features',
            },
            {
                path: '/usage',
                component: 'src/containers/Usage',
            },
            {
                path: '/about',
                component: 'src/containers/About',
            },
            {
                is404: true,
                component: 'src/containers/404',
            },
        ]
    },
}
