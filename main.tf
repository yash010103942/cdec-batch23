module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.0"

  cluster_name    = "my-eks-cluster"
  cluster_version = "1.29"

  subnet_ids = ["subnet-00e26a0e772435c72", "subnet-032092d1677fe84f6"]
  vpc_id     = "vpc-0a3f4db7781994c0b"

  eks_managed_node_groups = {
    default = {
      desired_size = 2
      max_size     = 3
      min_size     = 1

      instance_types = ["t3.medium"]
    }
  }
}
