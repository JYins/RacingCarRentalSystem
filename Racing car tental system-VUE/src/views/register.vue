<template>
	<div>
		<div class="container">
			<div class="login-form">
				<h1 class="h1" style="color:black;fontSize:28px;"> Create Account</h1><hr><br>
				<el-form ref="rgsForm" class="rgs-form" label-width="120px">
					<el-form-item label="Username" class="input" v-if="tableName == 'user'">
						<el-input v-model="ruleForm.username" autocomplete="off" placeholder="Username" />
					</el-form-item>
					<el-form-item label="Password" class="input" v-if="tableName == 'user'">
						<el-input v-model="ruleForm.password" autocomplete="off" placeholder="Password" type="password"
							elsetype="text" />
					</el-form-item>
					<el-form-item label="Full Name" class="input" v-if="tableName == 'user'">
						<el-input v-model="ruleForm.realName" autocomplete="off" placeholder="Full Name" />
					</el-form-item>
					<el-form-item label="ID Number" class="input" v-if="tableName == 'user'">
						<el-input v-model="ruleForm.identificationNumber" autocomplete="off" placeholder="ID Number" />
					</el-form-item>
					<el-form-item label="Phone" class="input" v-if="tableName == 'user'">
						<el-input v-model="ruleForm.mobilePhone" autocomplete="off" placeholder="Phone" />
					</el-form-item>
					<div>
						<el-button type="success" @click="login()"> Register</el-button>
						<el-button @click="close()" type="primary"> Back</el-button>
					</div>
				</el-form>
			</div>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {
			ruleForm: {
			},
			tableName: "",
			rules: {}
		};
	},
	mounted() {
		let table = this.$storage.get("loginTable");
		this.tableName = table;
	},
	methods: {
		// get uuid
		getUUID() {
			return new Date().getTime();
		},
		close() {
			this.$router.push({ path: "/login" });
		},
		// register handler
		login() {
			if ((!this.ruleForm.username) && `user` == this.tableName) {
				this.$message.error(`Username is required`);
				return
			}
			if ((!this.ruleForm.password) && `user` == this.tableName) {
				this.$message.error(`Password is required`);
				return
			}
				// ID and phone validation relaxed - any format accepted
			this.$http({
				url: `${this.tableName}/register`,
				method: "post",
				data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "Register success",
						type: "success",
						duration: 1000,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>
<style lang="scss" scoped>
.el-radio__input.is-checked .el-radio__inner {
	border-color: #00c292;
	background: #00c292;
}

.el-radio__input.is-checked .el-radio__inner {
	border-color: #00c292;
	background: #00c292;
}

.el-radio__input.is-checked .el-radio__inner {
	border-color: #00c292;
	background: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
	color: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
	color: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
	color: #00c292;
}

.h1 {
	margin-top: 10px;
}

body {
	padding: 0;
	margin: 0;
}

.nk-navigation {
	margin-top: 15px;

	a {
		display: inline-block;
		color: #fff;
		background: rgba(255, 255, 255, .2);
		width: 100px;
		height: 50px;
		border-radius: 30px;
		text-align: center;
		display: flex;
		align-items: center;
		margin: 0 auto;
		justify-content: center;
		padding: 0 20px;
	}

	.icon {
		margin-left: 10px;
		width: 30px;
		height: 30px;
	}
}

.register-container {
	margin-top: 10px;

	a {
		display: inline-block;
		color: #fff;
		max-width: 500px;
		height: 50px;
		border-radius: 30px;
		text-align: center;
		display: flex;
		align-items: center;
		margin: 0 auto;
		justify-content: center;
		padding: 0 20px;

		div {
			margin-left: 10px;
		}
	}
}

.container {
	background-image: url("../assets/img/bg.jpg");
	height: 100vh;
	background-position: center center;
	background-size: cover;
	background-repeat: no-repeat;

	.login-form {
		right: 50%;
		top: 50%;
		height: auto;
		transform: translate3d(50%, -50%, 0);
		border-radius: 10px;
		background-color: white;
		width: 420px;
		padding: 30px 30px 40px 30px;
		font-size: 14px;
		font-weight: 500;

		.h1 {
			margin: 0;
			text-align: center;
			line-height: 54px;
			font-size: 24px;
			color: #000;
		}

		.rgs-form {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			.input {
				width: 100%;

				& ::v-deep .el-form-item__label {
					line-height: 40px;
					color: 14px;
					font-size: #606266;
				}

				& ::v-deep .el-input__inner {
					height: 40px;
					color: #606266;
					font-size: 14px;
					border-width: 1px;
					border-style: solid;
					border-color: #606266;
					border-radius: 4px;
					background-color: #fff;
				}
			}

			.btn {
				margin: 0 10px;
				width: 88px;
				height: 44px;
				color: #fff;
				font-size: 14px;
				border-width: 1px;
				border-style: solid;
				border-color: #409EFF;
				border-radius: 4px;
				background-color: #409EFF;
			}

			.close {
				margin: 0 10px;
				width: 88px;
				height: 45px;
				color: #409EFF;
				font-size: 14px;
				border-width: 1px;
				border-style: solid;
				border-color: #409EFF;
				border-radius: 4px;
				background-color: #FFF;
			}

		}
	}
}
</style>
