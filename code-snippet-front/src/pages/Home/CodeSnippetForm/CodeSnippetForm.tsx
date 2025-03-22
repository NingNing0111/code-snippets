import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { DialogDescription } from "@radix-ui/react-dialog";
import { useState } from "react";

type FormData = {
  title: string;
  content: string;
  description: string;
  language: string;
  category: string;
};
interface Props {
  btnName: string;
  initValue?: FormData;
  onSubmit: (e: FormData) => void;
}
const CodeSnippetForm = (props: Props) => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [isOpen, setIsOpen] = useState(false);

  const handleSubmit = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const title = (document.getElementById("title") as HTMLInputElement).value;
    const content = (document.getElementById("content") as HTMLTextAreaElement)
      .value;
    const description = (
      document.getElementById("description") as HTMLTextAreaElement
    ).value;
    const language = (document.getElementById("language") as HTMLInputElement)
      .value;
    const category = (document.getElementById("category") as HTMLInputElement)
      .value;
    const formData: FormData = {
      title,
      content,
      description,
      language,
      category,
    };
    props.onSubmit(formData);
    setIsOpen(false); // Close the dialog after submission
  };

  return (
    <div className="ml-2">
      <Dialog open={isOpen} onOpenChange={setIsOpen}>
        <DialogTrigger asChild>
          <Button variant="outline" onClick={() => setIsOpen(true)}>
            新增
          </Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[425px]">
          <DialogHeader>
            <DialogTitle>新增代码片段</DialogTitle>
            <DialogDescription>密码用于校验身份</DialogDescription>
          </DialogHeader>
          <div className="grid gap-4 py-4">
            <div className="grid grid-cols-4 items-center gap-2">
              {" "}
              {/* Adjust the gap here */}
              <Label className="text-right">标题</Label>
              <Input
                id="title"
                defaultValue={props.initValue?.title}
                className="col-span-3"
                required
                placeholder="请输入标题"
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-2">
              {" "}
              {/* Adjust the gap here */}
              <Label className="text-right">语言</Label>
              <Input
                id="language"
                defaultValue={props.initValue?.language}
                className="col-span-3"
                required
                placeholder="请输入语言"
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-2">
              {" "}
              {/* Adjust the gap here */}
              <Label className="text-right">类别</Label>
              <Input
                id="category"
                defaultValue={props.initValue?.category}
                className="col-span-3"
                required
                placeholder="请输入类别"
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-2">
              {" "}
              {/* Adjust the gap here */}
              <Label className="text-right">描述</Label>
              <Textarea
                id="description"
                defaultValue={props.initValue?.description}
                className="col-span-3"
                placeholder="请输入描述..."
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-2">
              <Label className="text-right">代码</Label>
              <Textarea
                id="content"
                defaultValue={props.initValue?.content}
                className="col-span-3"
                required
                placeholder="请输入代码"
              />
            </div>
            <div className="grid grid-cols-4 items-center gap-2">
              <Label className="text-right">密码</Label>
              <div className="relative col-span-3">
                <Input
                  id="password"
                  className="col-span-3"
                  required
                  placeholder="请输入密码"
                  type={passwordVisible ? "text" : "password"}
                />
                <button
                  type="button"
                  className="absolute right-2 top-1/2 transform -translate-y-1/2"
                  onClick={() => setPasswordVisible(!passwordVisible)}
                >
                  {passwordVisible ? "隐藏" : "显示"}
                </button>
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button type="submit" onClick={handleSubmit}>
              确定
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default CodeSnippetForm;
