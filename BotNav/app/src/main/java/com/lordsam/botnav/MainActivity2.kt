package com.lordsam.botnav


class MainActivity2 : BaseActivity() {

    override fun getLayoutId() :Int {
        return R.layout.activity_main2
    }

    override fun getBottomNavigationMenuItemId() :Int{
        return R.id.second
    }
}