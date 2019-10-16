package com.leeda.parrot.member.persistence

import com.leeda.parrot.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>