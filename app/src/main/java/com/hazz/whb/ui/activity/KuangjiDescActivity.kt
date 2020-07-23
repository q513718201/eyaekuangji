package com.hazz.whb.ui.activity

import androidx.appcompat.widget.Toolbar
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Msg
import com.hazz.whb.mvp.presenter.MsgPresenter
import com.hazz.whb.ui.adapter.MsgAdapter
import com.hazz.whb.utils.ToolBarCustom
import kotlinx.android.synthetic.main.kuangji_desc.*


class KuangjiDescActivity : BaseActivity(), LoginContract.MsgView {


    override fun getMsg(msg: List<Msg>) {

    }


    private var mCoinPresenter: MsgPresenter = MsgPresenter(this)

    override fun layoutId(): Int {
        return R.layout.kuangji_desc
    }

    override fun initData() {

    }


    private var mAdapter: MsgAdapter? = null
    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("详情")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setTitleColor(resources.getColor(R.color.color_white))
                .setOnLeftIconClickListener { view -> finish() }

        val title = intent.getStringExtra("name")

        tv_title.text=title
        var content="坚持最高品值、最高速度、最高寛带内存数据存取，采用美光 Micron Technology 第四代双倍数据率同步动态随机存取内存 (Double-Data-Rate Fourth Generation Synchronous Dynamic Random Access Memory，简称为 DDR4 SDRAM)， 是一种高带宽的计算机内存规格。 \n" +
                "DDR4 相较于前代的 DDR3 的优势，主要是更高的模块密度（容量单位体积容量更大）、操作电压更低（功耗降低）以及带宽增加，其特色： \n" +
                "两倍速度 \n" +
                "更快速地加载应用，轻松运行要求苛刻的程序，无任何延迟。 Crucial DDR4 内存推出即可达到 2133 MHz，比 DDR3 内存技术快 30% 以上。更厉害的是，随着DDR4 技术逐渐成熟，速度有望达到 3200 MHz，比起 DDR3 内存快上两倍。 \n" +
                "两倍带宽 \n" +
                "Crucial DDR4 将内存带宽增加了 30% 以上，使您的系统能够同时处理更多数据。随着 DDR4 技术逐渐成熟，带宽有望达到 25.6 GB/s，是标准 DDR3 的两倍。为实现最大带宽，Crucial DDR4 内存针对支持四信道内存的下一代平台进行了优化。 \n" +
                "更加节能  \n" +
                "消耗更少的电力，实现更高的性能。标配 DDR3 内存的工作电压为 1.5V，而Crucial DDR4 仅为 1.2V，因此可比标配 DDR3 技术节省 20% 的电压。 Crucial DDR4 模块还具备其他效率特征，使总体功耗减少高达 40%。\n\n" +
                "eyeM5 HPC Cluster 超算中心布署 \n" +
                "1.1000 node 台 eyeM5 201 组成超算中心。 \n" +
                "2. 算力 3T *1000 台 =3P 整数算力速度。 \n" +
                "3. 硬盘容量 8T *1000 台 =8P 储存空间整。  "

        tv_content.text=content

       // Glide.with(this).load(R.mipmap.kuangji1).into(iv)
    }

    override fun start() {


    }


}
