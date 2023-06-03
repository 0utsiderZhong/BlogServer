const state = {
    /**
     * 备案号
     */
    F_ICP: 'ChatGPT',
    F_Title: 'ClockBlog',
    F_Title_Desc: '每临绝境峰回路转',
    /**
     * 版权
     */
    F_Copyright: "Copyright © 2023 - 2023",
    F_Copyright_Desc: "以商业目的使用本网站内容需获许可，非商业目的使用授权遵循CC BY-NC 4.0",
    F_Copyright_Desc_En: "All content is made available under the CC BY-NC 4.0 for non-commercial use. Commercial use of this content is prohibited without explicit permission.",
};

const mutations = {
    CHANGE_SETTING: (state, {key, value}) => {
        if (state.hasOwnProperty(key)) {
            state[key] = value
        }
    }
};

const actions = {
    changeSetting({commit}, data) {
        commit('CHANGE_SETTING', data)
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
}

