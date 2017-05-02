package com.bob.core.base

import com.bob.core.base.entity.BaseEntity
import com.bob.core.base.mapper.BaseMapper
import org.jetbrains.annotations.TestOnly
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Commit
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * Created by Bob on 2017/5/2.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = arrayOf("/applicationContext.xml", "/spring-mybatis.xml", "/aop.xml"))
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class, DirtiesContextTestExecutionListener::class)
@Transactional
//@Rollback  // 回滚，取消测试数据
@Commit // 提交，保持测试数据
class BaseMapperKtTest : AbstractTransactionalJUnit4SpringContextTests() {

    private val log = LoggerFactory.getLogger(BaseMapperKtTest::class.java)

    private var id: String? = null

    @Autowired
    @Suppress("SpringKotlinAutowiring")
    lateinit var baseMapper: BaseMapper<BaseEntity>

    fun getBaseEntity(): BaseEntity {
        return BaseEntity()
    }

    // BeforeClass 和 AfterClass 必须是静态方法
    companion object Initailizer {
        @TestOnly
        @JvmStatic
        @BeforeClass
        fun start() {
            println("start")
//            log.debug("=====================  start  =======================")
        }

        @TestOnly
        @JvmStatic
        @AfterClass
        fun end() {
            println("end")
//            log.debug("=====================  end  =======================")
        }
    }

    @Test
    fun M01_insertTest() {
        val baseEntity = getBaseEntity()
        baseMapper.insert(baseEntity)
        Assert.assertNotNull(baseEntity.id)
        id = baseEntity.id
    }

    @Test
    fun M02_selectTest() {
        val baseEntity = getBaseEntity()
        baseEntity.id?.let { id }
        var select: BaseEntity = baseMapper.select(baseEntity)
        Assert.assertNotNull(select)
        Assert.assertNotNull(select.id)
    }

    @Test
    fun M03_updateTest() {
        val baseEntity = getBaseEntity()
        baseEntity.id?.let { id }
        var select1: BaseEntity = baseMapper.select(baseEntity)
        baseEntity.createTime = LocalDateTime.now()
        baseMapper.update(baseEntity)
        var select2: BaseEntity = baseMapper.select(baseEntity)
        Assert.assertNotEquals(select1.updateTime, select2.updateTime)
    }

    @Test
    fun M04_deleteTest() {
        val baseEntity = getBaseEntity()
        baseEntity.id?.let { id }
        val delete = baseMapper.delete(baseEntity)
        Assert.assertEquals(delete, 1)
        var select: BaseEntity = baseMapper.select(baseEntity)
        Assert.assertNull(select)
    }

}


